<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/24
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.net.InetAddress"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
    <app:css />
    <script language="Javascript" src="/cm/js/time.js" ></script>
    <script language="Javascript" src="/cm/js/jquery-1.11.3.min.js" ></script>
    <script type="text/javascript">
        // 用户注销
        function logOff(){
            $.get('../Login/LogOff');
            location.href="../login.html";
        }
    </script>

</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="getLangDate()">
<p>11</p>
<%--<div class="top">
    <div class="fr">
        <div style="margin-left: 80%; margin-top: 1%; font-size: 28px">
            用户名:<span id="loginName" ></span><br>
            <span style="font-size: 20px">
    <a href="javascript:logOff()">注销</a>|
    &lt;%&ndash;<a href="javascript:ModifyPassword()">修改密码</a>&ndash;%&gt;
    </span>
        </div>
    </div>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:none">
    <tr>

    </tr>
</table>--%>
</body>
</html>
