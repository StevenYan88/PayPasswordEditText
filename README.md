### 打造一款属于自己的支付密码输入框

### 效果图
![device-2018-07-10-112505.gif](https://upload-images.jianshu.io/upload_images/1472453-b2dabfaa93389b77.gif?imageMogr2/auto-orient/strip)


![ps.png](https://upload-images.jianshu.io/upload_images/1472453-8d91c2e0a2c7fb59.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  

看这样一张图 我们首先需要绘制这样一个View继承系统自带的EditText    
 
     1. 绘制外围Rect
     2. 绘制分割线
     3. 绘制圆点密码
        
大家看到边框、分割线、圆点密码的颜色、大小、都是通过自定义属性而获得，  
在写布局文件都可以自定义，从而实现不同的效果的支付密码输入框



**License**  

    Copyright 2018 StevenYan88
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0  
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
