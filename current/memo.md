ubuntu 常用命令

视频剪辑和转码
-----------------
<a ffmpeg  -i source.ts -vcodec copy -acodec copy -ss 00:10:24 -to 00:18:39 arashi.ts -y //剪切视频 >
<a ffmpeg -i aa.ts -strict -2 io.avi.mp4 //将ts文件转换成mp4文件>
ffmpeg  -i source.ts -vcodec copy -acodec copy -ss 00:10:22 -to 00:18:39 arashi.ts -y
ffmpeg  -i source.ts -vcodec copy -acodec copy -ss 03:23:07 -to 03:25:55 arashi1.ts -y
ffmpeg  -i source.ts -vcodec copy -acodec copy -ss 03:28:38 -to 03:35:08 arashi2.ts -y
ffmpeg  -i source.ts -vcodec copy -acodec copy -ss 04:16:28 -to 04:21:01 arashi3.ts -y
ffmpeg  -i source.ts -vcodec copy -acodec copy -ss 04:34:47 -to 04:39:49 arashi4.ts -y

001024 001839		032307 032555         032838 033508      041628 042101      043447 043949																														
																																																																																										
Git命令大全
-----------------
git clone https://github.com/liyanippon/working.git //克隆代码
git add test.txt //添加文件
git commit -m "[add]添加文件"//提交文件
git fetch origin master //更新远程代码到本地库
git pull origin master //从远程获取最新版本并merge到本地
git push origin master//将本地的master分支推送到origin主机的master分支
git status//查看是否有新添加未提交的文件
git重命名文件和文件夹
git mv -f oldfolder newfolder
git add -u newfolder (-u选项会更新已经追踪的文件和文件夹)
git commit -m "changed the foldername whaddup"
git mv foldername tempname && git mv tempname folderName (在大小写不敏感的系统中，如windows，重命名文件的大小写,使用临时文件名)
git mv -n foldername folderName (显示重命名会发生的改变，不进行重命名操作)


人生予感










