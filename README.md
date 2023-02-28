# zFundRaiserBE

要跑上面的代码，需要先有一个database叫zfundraiser, 可以通过create database zfundraiser;

application.properties里jdbc的相关的配置需要根据本地mysql的用户名，密码进行修改

然后可以直接运行代码，代码会自动生成相应的table

之后需要手动提前建立一个category, category如果叫分组赛的话，excel里upload的match也要相应的填分组赛，比如:
![image](https://user-images.githubusercontent.com/115074615/202417888-807b6e6e-2cfa-420d-9541-610646adc27e.png)

然后还需要建一个rule，比如:
![image](https://user-images.githubusercontent.com/115074615/202418830-3660edd4-3222-421b-a1c8-4aa9b1124d87.png)

然后就可以用github里的excel模板来上传数据了，用upload接口 (截图里的是服务器的url，本地的url是http://localhost:8081/upload):

![image](https://user-images.githubusercontent.com/115074615/202419029-d7ab3269-59bf-4a4f-8524-0b154474efed.png)
