<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>template</title>
  <style type="text/css">
    .button { /* 按钮美化 */
      width: 100px; /* 宽度 */
      height: 40px; /* 高度 */
      border-width: 0px; /* 边框宽度 */
      border-radius: 3px; /* 边框半径 */
      background: #1E90FF; /* 背景颜色 */
      cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
      outline: none; /* 不显示轮廓线 */
      font-family: Microsoft YaHei; /* 设置字体 */
      color: white; /* 字体颜色 */
      font-size: 17px; /* 字体大小 */
    }
    .button:hover { /* 鼠标移入按钮范围时改变颜色 */
      background: #5599FF;
    }
  </style>
</head>
<body>

<div>
  <form action="/inner/tactics/open.json">
  <button type="submit" class="button">开启策略</button>
  </form>
</div>

<div style="margin-top: 10px">
  <form action="/inner/tactics/close.json">
  <button type="submit" class="button">关闭策略</button>
  </form>
</div>
</body>

</html>
