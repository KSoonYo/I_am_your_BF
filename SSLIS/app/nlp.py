#-*- coding: utf-8 -*-
from PyKomoran import *
from .stopword import StopWord

komoran = Komoran(DEFAULT_MODEL['FULL'])

# 자막파일을 입력받아 수어에 사용되는 형태소를 재배치하는 함수를 가진 객체
class NLP:
    def __init__(self):
        self.komoran = komoran
        # self.komoran.set_user_dic(r"C:/Users/SSAFY/Desktop/ssafy공통PJT/pjt_backup/SSLIS/app/bin/dic.user")
        self.pr = StopWord()
        pass

    def splitLine(self, line):
        for i in range(len(line)):
            s = str(line[i])
            s = s.split('/')
            line[i] = s
        return line

    # 수어에 사용되는 형태소를 재배치하는 함수
    def relocateMorpheme(self, subtitle_path):
        result=[]
        word_list=[]
        morph_list=[]

        line = self.komoran.get_list(subtitle_path)
        line = self.splitLine(line)
        for w, m in line:
            r, word, morph = self.pr.process_morph(m, w)
            if r == 1:
                if (word == 'ㅂ니까') or (word == '하다') or (word == '끝'):
                    if len(result) == 0:
                        word_list.append(word)
                        morph_list.append(morph)
                    elif result[len(result) - 1][0] != word:
                        word_list.append(word)
                        morph_list.append(morph)
                else:
                    word_list.append(word)
                    morph_list.append(morph)
        result.append(word_list)
        result.append(morph_list)
        return result[0], result[1]


# nlp = NLP()
# print(nlp.relocateMorpheme('나는 사과와 배를 먹는다. 나는 너에게 사과한다. 예쁜'))

# 사과 = 명사, 동사, 형용사, 부사
# 1. 동음이의어 비교
# 2. 품사가 같은 단어 찾기
# 3. 설명이 같은 단어 찾기
# 4. ref단어 찾기
