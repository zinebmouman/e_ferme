<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Consommateur</title>
</head>
<body>
    <h1>Formulaire d'ajout d'un consommateur</h1>

    <form action="${pageContext.request.contextPath}/ajouterConsommateur" method="POST">
        <label for="Nom">Nom:</label>
        <input type="text" id="Nom" name="Nom" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required><br><br>

        <label for="password">Mot de passe:</label>
        <input type="password" id="password" name="password" required><br><br>

        <label for="telephone">Téléphone:</label>
        <input type="text" id="telephone" name="telephone" required><br><br>

        <label for="address">Adresse:</label>
        <textarea id="address" name="address" required></textarea><br><br>

        <button type="submit">Ajouter</button>
    </form>

</body>
</html>
