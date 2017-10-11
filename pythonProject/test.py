# -*- coding: utf-8 -*-
import sys
reload(sys)
sys.setdefaultencoding('utf-8')
import requests
url='https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=time&page_limit=20&page_start=0' #这里的url比较长
content=requests.get(url).content
print('>>>>>>>>>>>>>'+content)
