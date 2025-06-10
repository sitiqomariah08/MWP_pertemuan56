<?php
<?php
if (isset($_COOKIE['nama'])) {
    echo "Halo, " . htmlspecialchars($_COOKIE['nama']) . "!";
} else {
    echo "Cookie 'nama' tidak ada";
}
?>