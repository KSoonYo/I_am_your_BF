# -*- coding: utf-8 -*-

import codecs
import requests
import re
import cv2
from bs4 import BeautifulSoup
from moviepy.video.io.ffmpeg_tools import *
from moviepy.video.fx.crop import *
from moviepy.editor import *
import os
import urllib3
import json
from moviepy.video.io.VideoFileClip import VideoFileClip

os.environ.setdefault("DJANGO_SETTINGS_MODULE", "SignLanguage.settings")

import django

django.setup()

from app.models import *

# 한국수어사전 홈페이지에서 단어명, 품사, 뜻, 수어영상을 Crawling하는 함수


def signAPI():
    openApiURL = "http://api.kcisa.kr/API_CNV_054/request"
    accessKey = "d15d1b57-ecc5-43e3-b0e6-e9200ce7e92c"


    # requestJson = {
    #     "serviceKey": self.accessKey,
    #     "argument": {
    #         'first_word': firstWord,
    #         'second_word': secondWord
    #     }
    # }
    url = 'http://api.kcisa.kr/openapi/service/rest/meta13/getCTE01701'
    payload = {'serviceKey': '8e33704a-579f-4d01-8f7e-f641e2f9aa6f',
               'numOfRow': 5,
               'pageNo': 5}
    http = urllib3.PoolManager()
    req = http.request("GET",url, fields=payload)


    return req


def spider(max_indexes):
    f = open("basic.txt", 'w', encoding='utf8')
    f2 = open("nunmber.txt", "w", encoding='utf8')
    count1 = 31687
    count2 = 181

    index = max_indexes
    while index <= max_indexes:

        # url = 'https://sldict.korean.go.kr/front/sign/signContentsView.do?current_pos_index=0&origin_no=10127&searchWay=&top_category=CTE&category=&detailCategory=&searchKeyword=&pageIndex=1&pageJumpIndex='
        url = 'https://sldict.korean.go.kr/front/sign/signContentsView.do?origin_no=' + str(
            index)  # + '&category=SPE003'

        session = requests.Session()
        session.verify = False
        # URL에서 비디오 가져오기
        source_code = session.get(url)
        # source_code = requests.get(url)
        plain_text = source_code.text
        soup = BeautifulSoup(plain_text, 'lxml')

        if soup.title.get_text() != "국립국어원 한국수어사전":
            index += 1

        # word : 단어, part : 품사
        else:
            word = clean_text(soup.find("meta", property="og:title").get('content'))
            print(word)
            word = clean_word(word)
            print("word : " + word)
            temp1 = soup.find("form", {"name": "signViewForm"})
            temp2 = temp1.find("dt", {"class": ""})
            temp3 = temp2.find_next_sibling("dd")
            #print(temp3)
            #temp4 = clean_text_2(temp3)
            #print(temp4)
            try:
                part = clean_text(temp3.find_next_sibling("dd").text)
                mean = search_mean(part)
                part = search_part(part)
                part = change_part(part)
            except:
                part = ''
                mean = ''

            print(mean)
            # if search_part(part) == '수사':
            #     file.write(word + "\n" + search_part(part) + "\n" + search_mean(part) + "\n")
            # print("단어 : " + word + "\n" + "품사 : " + search_part(part) + "\n")
            ref_word = ''
            data = []

            for link in soup.select('input#preview'):
                href = link.get('value').replace('105X105.jpg', '700X466.mp4')
                print("href : " + href)
                frame = cut_video(href)

                if part == '수사':
                    print(mean)

                    location = save_signlanguage_video(href, frame, count2, 'number')
                    data.append([word, part, mean, ref_word, location])
                    for w, p, m, r, l in data:
                        f.write(word + " " + part + " " + mean + "\n")
                        Number(word=w, part=p, mean=m, ref_word=r, location=l).save()
                    count2 += 1

                else:

                    # part = '명사'
                    location = save_signlanguage_video(href, frame, index, 'basic')
                    data.append([word, part, mean, ref_word, location])
                    for w, p, m, r, l in data:
                        print(w, p, m, r, l)
                        f.write(word + " " + part + " " + mean + "\n")
                        Basic(word=w, part=p, mean=m, ref_word=r, location=l).save()
                    count1 += 1

                print("단어 : " + word + "\n품사 :" + part + "\n")
            index += 1
    f.close()


#
def clean_text_2(text):
    print(str(text))
    cleaned_text = text.replace(' ', '')
    # text = re.sub('</dd>', '', cleaned_text)
    # cleaned_text = re.sub(' ', '', text)
    return cleaned_text


# 필요없는 text 부분 제거
def clean_text(text):
    print("clean_text :" + text)
    cleaned_text = re.sub('한국수어사전_', '', text)
    text = re.sub('-', '', cleaned_text)
    text2 = re.sub('\s\s', '', text)
    return text2


def clean_word(text):
    cleaned_text = re.sub("[^가-힣ㄱ-ㅎ]", '', text)
    return cleaned_text


# 품사 정보 가져오기
def search_part(text):
    if ']' in text:
        return text[1:text.index(']')]
    else:
        return '없음'


def change_part(part):
    if part == '관·명' or part == '대명사' or part == '의존 명사' or part == '대·부':
        part = '명사'
    elif part == '감·명':
        part = '감탄사'
    elif part == '수·관':
        part = '수사'
    elif part == '보조 형용사' or part == '보조 형용사' or part == '보조 동사' or part == '형용사' or part == '동사':
        part = '용언'

    return part


# 뜻 정보 가져오기
def search_mean(text):
    if ']' in text:
        return text[text.index(']') + 1:]
    else:
        return '없음'


# 비디오에서 사용할 부분만 자르기
def cut_video(url):
    vcap = cv2.VideoCapture(url)
    #  vcap의 프레임 수 get
    frame_cnt = vcap.get(cv2.CAP_PROP_FRAME_COUNT)
    # 프레임 수 1/3로 나누기
    # frame_cnt = int(frame_cnt/3)
    frame = str(frame_cnt)
    return frame


def save_signlanguage_video(href, frame, count, type):
    input_location = 'app/media/' + type + '/' + str(count) + '.mp4'
    output_location = 'app/media/' + type + '/' + str(count) + '.mp4'
    output_location2 = 'app/media/' + type + '/' + str(count) + '.mp4'

    # if txt is not None:
    #    txt.encode('utf8')

    # end_time = int(frame)/30

    end_time = int(float(frame))
    print("end_time : " + str(end_time))

    ffmpeg_extract_subclip(href, 0, end_time, input_location)  # 파일경로, start, end, 저장될 파일 경로
    # ffmpeg_resize(input_location, output_location, (560, 360))
    # clip = VideoFileClip(output_location)
    # new_clip = crop(clip, x1=70, y1=0, x2= 490, y2=270)
    # new_clip.write_videofile(output_location2)

    return output_location2


# spider(10128)
#spider(9380)
response = signAPI()
response = response.data.decode('utf-8')
print(response)
#print(response.body['itmes'])