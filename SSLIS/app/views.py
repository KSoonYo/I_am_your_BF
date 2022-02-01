from django.shortcuts import render
from app.models import Basic, Finger, Number
from .bin.nlp import NLP
from .bin.similarityVoca import SimilarytyWord
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse

# Create your views here.
global nlp
nlp = NLP()


def matchingSign(Morpheme_path):   
    print('##Match Sign Start...')
    line = Morpheme_path # pre_text => 형태소형태로 분해한것 [[문자열, 숫자], [품사]]
    results = []
    words = line[0]
    idx=0
    not_found=[]
    pass_word=[]
    nums=['1','2','3','4','5','6','7','8','9','10','100','1000','10000','0'] # 12345 => 10000 2 1000 3 100 4 10 5
    
    for word in words: # [[1,2,3,4,5,6], 밥, 나는, 먹다]
        idx+=1
        try:
            # 형태소가 숫자일 때,
            if word[0] in nums:
                
                num_word=word.split()
                if num_word[0] == '5.18':
                    pass
                else :
                    try:
                        for word in num_word: # [['1'], '2', '3']
                            find_word = Number.objects.get(word=word) # ['1']
                            results.append(find_word.location) # 동영상 위치
                            print(find_word)
                            pass_word.append(word) # 단어 의미
                    except :
                        for word in num_word:
                            for str in word: # '1'
                                find_word = Number.objects.get(word=str)
                                results.append(find_word.location) # 동영상 위치
                                print(find_word)
                                pass_word.append(word) # 단어 의미
                

            # 형태소가 DB 검색 시 1개 일 때,
            elif Basic.objects.filter(word=word).count() == 1:
                find_word = Basic.objects.get(word=word)
                results.append(find_word.location)
                print(find_word,find_word.mean)
                pass_word.append(word)

            # 형태소가 DB 검색 시 2개 일 때,
            elif Basic.objects.filter(word=word).count() == 2:
                find_word = Basic.objects.filter(word=word)
                results.append(find_word[0].location) # 첫번째 데이터로 적용했기 때문에 정확도가 떨어짐
                print(find_word[0],find_word[0].mean)
                pass_word.append(word)

            # 형태소가 DB 검색 시 여러 개 일 때,  예시: 
            elif Basic.objects.filter(word=word).count() > 2:
                find_word = Basic.objects.filter(word=word)
                
                

    # ============유사 검사 실시=======================
                # 명사 list, 왼쪽 오른쪽 명사거리, 명사, 참조단어 list, href list
                noun = []
                nounSub = []
                refList = []
                locationList = []
                mean_list=[]
                N=''
                # 품사 리스트
                parts = line[1]  # 품사

                # 해당 단어의 왼쪽 방향 가까운 명사 찾기. => 직접할때 분석
                for i in range(idx-2, -1, -1): 
                    if(parts[i] == "명사"):
                        noun.append(words[i])
                        nounSub.append(idx - i -1)
                        break
                

                # 해당 단어의 오른쪽 방향 가까운 명사 찾기 => 직접할때 분석
                for i in range(idx, len(parts)): # 나는 사과와 배를 먹는다. => 나, 사과
                    if(parts[i] == "명사"):
                        noun.append(words[i])
                        nounSub.append(i-idx+1)
                        break

                if len(nounSub) > 1 :
                    if(nounSub[0]>nounSub[1]): # 어떻게 저장되길래 비교를하지?
                        N = noun[1]
                    else :
                        N = noun[0]
                elif len(nounSub) == 1:
                    N = noun[0]
                else:
                    results.append(find_word[0].location)
                    pass_word.append(word)
                    continue

                # 같은 품사 갯수
                samePart = 0
                href = ''
                # 일차적으로 품사가 일치하는 단어로 반환
                for result in find_word:
                    part_list=[]
                    if result.mean in mean_list:
                        continue
                    if result.part == parts[idx-1]:
                        samePart += 1
                        href = result.location
                    pre_text= nlp.relocateMorpheme(result.mean) # 형태소 분석해서 수어로 재배치
                    for i in range(len(pre_text[1])): # 품사들 수만큼 검사
                        if pre_text[1][i] =='명사': # 명사라면 추가
                            part_list.append(pre_text[0][i])
                    mean_list.append(result.mean)
                    refList.append(part_list[0]) # 확인이 필요하다 (첫번째 데이터를 더하는 이유??)
                    locationList.append(result.location)
                    
                if(samePart == 1):
                    results.append(href)
                    pass_word.append(word)
                else:
                    print(f'동음이의어 단어 : {word}, refList : {refList}, 가장 가까운 명사 : {N}')
                    sim = SimilarytyWord()
                    n = sim.calc_similarity(N, refList) # 숫자로 방출
                    if(n == -1): # 쓸모없는 단어인데 왜 주소를 저장하지?
                        results.append(locationList[0]) # => locationList[0] => 처음에 인식한 단어의 주소
                        pass_word.append(word)
                    else:
                        pass_word.append(word)
            else: # 형태소 검사 갯수가 DB에 없는경우
                not_found.append(word)
        except: # 이유 확인이 필요
            continue

    print(results)
    print('##Match Sign End')
    return results, not_found, pass_word

@csrf_exempt
def result(request):
    default_video='aws/media/sign/basic/24224.mp4'

    text = request.POST.get('text1')
    
    print('text : ',text)
    pre_text= nlp.relocateMorpheme(text) # 수어에 사용되는 형태소를 재배치하는 함수
    # [밥, 나는, 먹다]
    print('pre_text : ',pre_text)

    result, not_found_word, pass_word_list = matchingSign(pre_text) # results, not_found, pass_word
    print('href_list : ',result)
    print('없는 단어 리스트 : ',not_found_word)
    print('있는 단어 : ',pass_word_list)
    if result == []:
        result.append(default_video)

    context={
        'q':result
    }
    return JsonResponse(context)

