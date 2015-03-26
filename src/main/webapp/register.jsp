<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<form role="form" method="post" action="/register" class="form-horizontal">
    <div class="form-group">
        <div class="col-sm-2">
            <label for="first-name" class="control-label sr-only">First Name</label>
        </div>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="first-name" id="first-name" placeholder="First Name"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-2">
            <label for="last-name" class="control-label sr-only">Last Name</label>
        </div>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="last-name" id="last-name" placeholder="Last Name"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-2">
            <label for="email" class="control-label sr-only">Email</label>
        </div>
        <div class="col-sm-10">
            <input class="form-control" type="email" name="email" id="email" placeholder="Enter email"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-2">
            <label for="phone" class="sr-only"></label>
        </div>
        <div class="col-sm-10">
            <input class="form-control" type="tel" name="phone" id="phone" placeholder="87011234567"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-info">Submit</button>
        </div>
    </div>
</form>
</body>
</html>