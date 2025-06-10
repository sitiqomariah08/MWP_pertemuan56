<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nama dengan Cookies</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <main>
        <form id="namaForm" style="display: flex; align-items: center; gap: 16px;">
            <label for="nama">Masukkan Nama:</label>
            <input type="text" id="nama" name="nama" required>
            <button type="submit">Simpan</button>
        </form>
        <h2 id="tampilNama" style="margin-top: 24px; font-size: 1.1em;"></h2>
    </main>

    <script>
        function getCookie(name) {
            const value = `; ${document.cookie}`;
            const parts = value.split(`; ${name}=`);
            if (parts.length === 2) return parts.pop().split(';').shift();
        }

        function setCookie(name, value, days) {
            const expires = new Date(Date.now() + days * 864e5).toUTCString();
            document.cookie = `${name}=${value}; expires=${expires}; path=/`;
        }

        const tampilNama = document.getElementById("tampilNama");
        const namaForm = document.getElementById("namaForm");
        const namaInput = document.getElementById("nama");

        const namaCookie = getCookie("nama");
        if (namaCookie) {
            tampilNama.textContent = `Halo, ${namaCookie}!`;
        }

        namaForm.addEventListener("submit", function (e) {
            e.preventDefault();
            const nama = namaInput.value;
            setCookie("nama", nama, 7);
            tampilNama.textContent = `Halo, ${nama}!`;
            namaInput.value = "";
        });
    </script>
</body>

</html>