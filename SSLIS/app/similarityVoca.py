# -*- coding:utf-8 -*-
import urllib3
import json


class SimilarytyWord:
    def __init__(self):
        self.openApiURL = "http://aiopen.etri.re.kr:8000/WiseNLU"
        # "http://aiopen.etri.re.kr:8000/WiseNLU_spoken"
        self.accessKey = '1c43a99b-8d45-4388-9dca-3209c404696a' # "ba6cecf3-c11b-4518-addf-4286e7f2009c"
        self.openApiURL2 = "http://aiopen.etri.re.kr:8000/WiseWWN/WordRel"
        pass

    def similarity_voca(self, w1, w2):

        

        requestJson = {
            "access_key": self.accessKey,
            "argument": {
                'first_word': w1,
                'second_word': w2
            }
        }

        http = urllib3.PoolManager()
        response = http.request(
            "POST",
            self.openApiURL2,
            headers={"Content-Type": "application/json; charset=UTF-8"},
            body=json.dumps(requestJson)
        )
        res = response.data.decode('utf-8')
        data = json.loads(res)
        data = data['return_object']['WWN WordRelInfo']
        first_word = data['FirstWordInfo']
        second_word = data['SecondWordInfo']
        
        
        return first_word, second_word


    # def calc_similarity(self, true_word, ref_word):
    #     result_sim = []
    #     for i in range(len(ref_word)):
    #         response = self.similarity_voca(true_word, ref_word[i])
    #         response = json.loads(str(response.data, "utf-8"))
    #         sim = response["return_object"]["WWN WordRelInfo"]["WordRelInfo"]["Similarity"]
    #         for i in range(len(sim)):
    #             s = + sim[i]["SimScore"]

    #         a = s / len(sim)
    #         result_sim.append(a)
    #     if max(result_sim) <= 0.0:
    #         return -1
    #     return result_sim.index(max(result_sim))

    # def komoran(self, text):
    #     analysisCode = "morp"
    #     requestJson = {
    #         "access_key": self.accessKey,
    #         "argument": {
    #             "text": text,
    #             "analysis_code": analysisCode
    #         }
    #     }
    #     http = urllib3.PoolManager()
    #     response = http.request(
    #         "POST",
    #         self.openApiURL,
    #         headers={"Content-Type": "application/json; charset=UTF-8"},
    #         body=json.dumps(requestJson)
    #     )
    #     res = response.data.decode('utf-8')
    #     data = json.loads(res)
    #     print(data)
    #     data = data['return_object']['sentence']
    #     word = []
    #     ty = []
    #     for dic in data:
    #         for di in dic['morp']:
    #             word.append(di['lemma'])
    #             ty.append(Morph.MORPH[di['type']])
    #     return word, ty

# sim = SimilarytyWord()
# print(sim.similarity_voca('사과','배'))