<%@ page import="entities.Ville"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des villes</title>
    <style>
        body {
            font-family: 'Helvetica', sans-serif; /* Changement de police */
            margin: 20px;
            background-color: #e0f7fa; /* Bleu ciel */
            color: #333; /* Couleur du texte principale */
        }

        form {
            margin-bottom: 20px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        input {
            padding: 10px;
            margin-bottom: 10px;
            width: 50%;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            padding: 10px;
            background-color: #3498db; /* Bleu */
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 4px;
        }

        button:hover {
            background-color: #2980b9; /* Nuance plus foncée de bleu au survol */
        }

        h1 {
            font-size: 24px; /* Taille de la police pour le titre principal */
            color: #3498db; /* Bleu pour le titre principal */
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #3498db; /* Bleu */
            color: white;
        }

        a {
            color: #3498db;
            text-decoration: none;
            margin-right: 10px;
        }
    </style>
</head>
<body>
          <header class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="display-4">Gestion des villes</h1>
        </header>
         <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <form action="VilleController" method="post">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalCenterTitle">Ajouter une ville</h5>
                       
                                
                            
                        </div>
    <form action="VilleController" method="post">
        Nom : <input type="text" name="ville" required /> <br>
        <button type="submit">Enregistrer</button>
    </form>

    <h1>Liste des villes :</h1>
    <table id="villesTable">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${villes}" var="v">
                <tr>
                    <td>${v.id}</td>
                    <td>${v.nom}</td>
                    <td>
                        <!-- Formulaire pour modifier la ville -->
                        <form action="VilleController" method="post">
                            <input type="hidden" name="id" value="${v.id}">
                            <input type="text" name="newNom" placeholder="Nouveau nom" required>
                            <input type="hidden" name="action" value="edit">
                            <button type="submit">Modifier</button>
                        </form>
                        <!-- Formulaire pour supprimer la ville -->
                        <form action="VilleController" method="post">
                            <input type="hidden" name="action" value="delete" />
                            <input type="hidden" name="id" value="${v.id}" />
                            <button type="submit">Supprimer</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script>
        // Supprimer le contenu du tableau
        function clearTable() {
            document.getElementById("villesTable").getElementsByTagName('tbody')[0].innerHTML = "";
        }
    </script>
</body>
</html>
