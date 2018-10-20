<html>
<head>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <title>Home</title>
</head>
<br>
<style>

  article {
    padding: 50px;
    overflow: hidden;
  }
  p {
    font-family: times;
    font-size: 150%;
    font-weight: bold;
  }
  input.search {
    width: 10em;  height: 2.5em;
  }
  h1 {
    font-family: SansSerif;
    font-size: 75px;
    font-weight: bold;
  }
</style>

<div>

  <article>
    <h1 align="center">ONLINE AUCTION</h1>
    <p><img src='https://cdn.pixabay.com/photo/2017/07/10/23/49/club-2492011_960_720.jpg'
            style='position:fixed;top:0px;left:0px;width:100%;height:100%;z-index:-1;'></p>
    </br>
  </article>
</div>
<p align="center">
<br><br><br><br>
<form align="center" action = "login.jsp">
  <input type="submit" value="login" class="search"/>
</form><br>
<form align="center" action = "signup.jsp">
  <input type="submit" value="signup" class="search"/>
</form>
</p>
</body>
</html>
