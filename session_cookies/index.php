<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="login-box">
        <h2>Form Login</h2>
        <form method="POST" action="login.php">
            Username: <input type="text" name="username" required><br>
            Password: <input type="password" name="password" required><br>
            <label><input type="checkbox" name="remember"> Remember Me</label><br><br>
            <input type="submit" value="Login">
        </form>
    </div>
</body>

</html>