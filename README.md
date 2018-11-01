# android app签名获取工具
project下是android 工程文件
otherTools是其他的android 签名获取工具
# 获取rpk签名md的方法
sed '1d;$d' certificate.pem | base64 --decode | md5sum
