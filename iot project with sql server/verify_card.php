<?php

$servername = "localhost";
$username = "Arduino";
$password = "ArduinoGetStarted.com";
$dbname = "db_3070";

// Get card ID from the GET request
if(isset($_GET["cardID"])) {
    $cardID = $_GET["cardID"];

    // Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);

    // Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    // SQL query to check if the cardID exists in the 'card' table
    $sql = "SELECT * FROM card WHERE cardID = $cardID";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        echo "Access granted"; // Card ID found in the database
    } else {
        echo "Access denied"; // Card ID not found in the database
    }

    $conn->close();
} else {
    echo "No card ID received";
}
?>
