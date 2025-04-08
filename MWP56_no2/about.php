<!DOCTYPE html>
<html lang="id">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Majestic Donuts</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>

    <nav>
        <a href="index.php">Home</a> |
        <a href="about.php">About</a>
    </nav>

    <div class="about">
        <div class="image-container">
            <img src="assets/doughnut.png" alt="Majestic Donut" />
        </div>

        <div class="about-content">
            <h1>Kenapa harus <strong>Majestic?🍩👑</strong></h1>
            <ul>
                <li>✔ <strong>Kualitas Terbaik</strong> – Kami hanya menggunakan bahan pilihan untuk memastikan setiap
                    gigitan selalu fresh dan nikmat.</li>
                <li>✔ <strong>Rasa Premium</strong> – Kombinasi rasa unik yang dibuat dengan penuh cinta dan inovasi.
                </li>
                <li>✔ <strong>Desain & Kemasan Mewah</strong> – Hadir dalam kemasan elegan yang cocok untuk dinikmati
                    sendiri atau diberikan sebagai hadiah spesial.</li>
                <li>✔ <strong>Aroma Menggoda</strong> – Setiap donat yang baru keluar dari dapur memiliki wangi manis
                    yang sulit ditolak.</li>
            </ul>
            <p>
                Dengan tagline <strong>"Taste the Royalty"</strong>, kami menghadirkan donat yang bukan hanya lezat,
                tetapi juga memberikan pengalaman megah dan istimewa dalam setiap gigitannya.
            </p>
            <h2>Majestic Donut – Karena Setiap Momen Layak Dihiasi dengan Kelezatan! ✨</h2>
        </div>

        <button id="showPopup" class="popup-btn">Lihat Promo 🎉</button>

        <div id="popup" class="popup-overlay">
            <div class="popup">
                <h2>Promo Spesial! 🎊</h2>
                <p>Beli 5 donat, GRATIS 1! Promo berlaku hingga akhir bulan ini.</p>
                <button id="closePopup" class="close-btn">Tutup</button>
            </div>
        </div>

        <script src="js/app.js"></script>
        <script src="js/popup.js"></script>
    </div>

    <div id="about"></div>

    <script src="about.js"></script>
</body>

</html>