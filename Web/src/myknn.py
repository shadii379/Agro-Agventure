import math
from tkinter import Tk, Label, Entry

import numpy as np
import matplotlib.pyplot as plt
import pymysql
from matplotlib import style
# import pandas as pd
import random
from collections import Counter
# from sklearn import preprocessing
import time

#for plotting
X=[]
y=[]
con = pymysql.connect(host="localhost", port=3306, db="agro agventure", user="root", passwd="")
cmd = con.cursor()
class CKNN:

    def __init__(self):
        self.accurate_predictions = 0
        self.total_predictions = 0
        self.accuracy = 0.0
        ##########
        # with open('labels.dat') as f:
        #     lines = f.readlines()#crpid
        # lines=[s.strip('\n') for s in lines]
        # training_data=np.loadtxt("sample.data",dtype=float,delimiter=" ")#data
        #


        cmd.execute("select * from details")
        data = cmd.fetchall()
        # print(data)

        for r in data:
            row = []

            row.append((float(r[2])))
            row.append((float(r[3])))
            row.append((float(r[4])))

            # row.append((data['humidity'][r]))
            # row.append((data['wet'][r]))

            X.append(row)

            y.append((str(r[1])))
            lines=y
            training_data=X
            # print("id",lines)
            # print("vl",training_data)

        self.training_set= { '6':[],'8':[],'9':[],'10':[],'11':[],'15':[],'16':[],'19':[]}#idsss
        # test_set = {2: [], 4:[]}

        #Split data into training and test for cross validation
        #training_data = lbls[: len(lbls)]
        test_data = []#[-int(test_size * len(dataset)):]

        #Insert data into the training set
        cnt=0

        for record in training_data:
            st=lines[cnt]
            cnt+=1


            self.training_set[str(st)].append( record[:])

    #########

    def predict(self,  to_predict, k = 3):

        # print(to_predict)
        distributions = []
        for group in self.training_set:
            i=0
            print(group,'group')
            for features in self.training_set[group]:
                # print(features)

                euclidean_distance = np.linalg.norm(np.array(features)- np.array(to_predict))
                # print("ft",np.array(features))
                # print("pr",np.array(to_predict))
                # print(euclidean_distance)
                # if euclidean_distance<=10:
                # if  group=='6':
                #     # print('hi',euclidean_distance,training_data[group],len(training_data[group]),len(to_predict),i)
                #     i+=1
                distributions.append([euclidean_distance, group])

        # print(distributions)
        results = [i[1] for i in sorted(distributions)[:k]]

        # result = Counter(results).most_common(1)[0]
        # print("rs",results,self.training_set.keys())
        #confidence = Counter(results).most_common(1)[0][1]/k

        return results



def prep(val):



    # feat=glcm_feat(filename)
    knn = CKNN()
    res=knn.predict(val)
    res=','.join(res)


    return res


# print(prep([1,2,3]))