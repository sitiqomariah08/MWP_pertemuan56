<?php
session_start();

// Hapus session dan cookie
session_unset();
session_destroy();
setcookie('username', '', time() - 3600, "/");

header("Location: index.php");
exit;
?>