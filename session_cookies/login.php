<?php
session_start();

$valid_user = "admin";
$valid_pass = "12345";

$username = $_POST['username'];
$password = $_POST['password'];

if ($username === $valid_user && $password === $valid_pass) {
    $_SESSION['username'] = $username;

    // Simpan cookie jika centang
    if (isset($_POST['remember'])) {
        setcookie('username', $username, time() + (604800), "/");
    }

    header("Location: dashboard.php");
    exit;
} else {
    echo "<script>alert('Login gagal! Username atau password salah.'); window.location='index.php';</script>";
}
?>