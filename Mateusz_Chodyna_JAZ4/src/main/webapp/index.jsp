<!DOCTYPE html>
<html>
<head>
    <title>Pogoda</title>
    <link href="mystyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="myDiv">
    <table style="with: 50%">
        <center>
    <h1>Wybierz miasto</h1>
            <br><form method="post" action="sprawdzPogode" >
            <br><select name ="wybraneMiasto">
                <option value="warszawa">Warszawa</option>
                <option value="gdansk">Gdansk</option>
                <option value="krakow">Krakow</option>
                <option value="wroclaw">Wroclaw</option>
                <option value="poznan">Poznan</option>
                <option value="lodz">Lodz</option>
                <option value="katowice">Katowice</option>
            </select>
            <br><br><br><br><input type="submit" value="Sprawdz">
        </form>
        </center>
    </table>
</form>
</div>
</body>
</html>