<%@ page import="entities.Hotel"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gestion des hôtels</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #eaeaea;
}

header {
	background-color: #007bff;
	color: #FF0000;
	padding: 20px;
	text-align: center;
}

main {
	max-width: 800px;
	margin: 20px auto;
	background-color: #FF0000;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

form {
	margin-bottom: 20px;
}

label {
	display: block;
	margin-bottom: 8px;
	font-weight: bold;
	color: #007bff;
}

input {
	width: 50%;
	padding: 12px;
	margin-bottom: 12px;
	box-sizing: border-box;
	border: 1px solid #3498db;
	border-radius: 6px;
	font-size: 16px;
}

button {
	background-color: #FF0000;
	color: white;
	padding: 14px 22px;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	font-size: 18px;
}

button:hover {
	background-color: #219d54;
}

h1 {
	color: #333;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid #ddd;
	padding: 14px;
	text-align: left;
	font-size: 16px;
}

th {
	background-color: #FF0000;
	color: white;
}

.modal-dialog {
	margin-top: 50px;
}
</style>
</head>
<body>
	<div class="container">
		<header class="d-flex justify-content-between align-items-center mb-4">
			<h1 class="display-4">Gestion des hotels</h1>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#exampleModalCenter">Ajouter un hotel</button>
		</header>
		 <form action="HotelController" method="post" class="mb-4">
            <div class="form-group">
                <label for="filterHotel">Filtre par Ville:</label>
                <select name="filterHotel" class="form-control">
                    <option value="0">All</option>
                    <c:forEach items="${hotels}" var="h">
                        <option value="${h.id}">${h.adresse} </option>
                    </c:forEach>
                </select>
                <input type="hidden" name="action" value="filterByFiliere">
            </div>
            <button type="submit" class="btn btn-primary">Filter</button>
        </form>

		<div class="modal fade" id="exampleModalCenter" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<form action="HotelController" method="post">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalCenterTitle">Ajouter
								un hotel</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<label class="custom-modal-label" for="nom">Nom:</label> <input
								type="text" name="nom" class="form-control" required><br>

							<label class="custom-modal-label" for="adresse">Adresse:</label>
							<input type="text" name="adresse" class="form-control" required><br>

							<label class="custom-modal-label" for="telephone">Telephone:</label>
							<input type="text" name="telephone" class="form-control" required><br>
							<label class="custom-modal-label" for="ville">Ville:</label>
<input type="text" name="ville" class="form-control" required><br><br>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Fermer</button>
							<input type="submit" class="btn btn-primary" value="Enregistrer">
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="modal fade" id="ModifyHotelModal" tabindex="-1"
			role="dialog" aria-labelledby="ModifyHotelModalTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<form action="HotelController" method="post">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="ModifyHotelModalTitle">Modifier
								un hotel</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Fermer">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<label class="custom-modal-label" for="id">Id:</label> <input
								type="text" name="id" class="form-control" id="modalHotelId"
								required><br> <label class="custom-modal-label"
								for="nom">Nom:</label> <input type="text" name="nom"
								class="form-control" id="modalHotelNom" required><br>

							<label class="custom-modal-label" for="adresse">Adresse:</label>
							<input type="text" name="adresse" class="form-control"
								id="modalHotelAdresse" required><br> <label
								class="custom-modal-label" for="telephone">Telephone:</label> <input
								type="text" name="telephone" class="form-control"
								id="modalHotelTelephone" required><br> <input
								type="hidden" name="action" value="edit"> <input
								type="hidden" name="id" id="modalHotelId">
								
								
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Fermer</button>
							<input type="submit" class="btn btn-primary"
								value="Enregistrer les changements">
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<form action="HotelController" method="get">
    <label for="ville">Filtrer par ville:</label>
    <input type="text" name="ville" id="ville">
    <button type="submit">Filtrer</button>
</form>

		<h1>Liste des hôtels :</h1>
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nom</th>
					<th>Adresse</th>
					<th>Téléphone</th>
					<th>Ville</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${hotels}" var="h">
					<tr>
						<td>${h.id}</td>
						<td>${h.nom}</td>
						<td>${h.adresse}</td>
						<td>${h.telephone}</td>
						<td>${h.Ville}</td>
						<td>
							<!-- Formulaire pour modifier la ville -->
							<button type="button" class="btn btn-secondary ml-2"
								data-toggle="modal" data-target="#ModifyHotelModal"
								data-hotel-id="${h.id}" data-hotel-nom="${h.nom}"
								data-hotel-adresse="${h.adresse}"
								data-hotel-telephone="${h.telephone}"
								data-hotel-ville="${h.Ville}">Modifier</button> <!-- Formulaire pour supprimer la ville -->
							<form action="HotelController" method="post" class="d-inline">
								<input type="hidden" name="action" value="delete" /> <input
									type="hidden" name="id" value="${h.id}" />
								<button type="submit" class="btn btn-danger">Supprimer</button>
							</form>
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

	<script>
		// Remove this function if not needed
		function clearTable() {
			document.querySelector('tbody').innerHTML = "";
		}
	</script>

	<script>
		$('#ModifyHotelModal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget);
			var hotelNom = button.data('hotel-nom');
			var hotelAdresse = button.data('hotel-adresse');
			var hotelTelephone = button.data('hotel-telephone');
			var modal = $(this);

			modal.find('#modalHotelNom').val(hotelNom);
			modal.find('#modalHotelAdresse').val(hotelAdresse);
			modal.find('#modalHotelTelephone').val(hotelTelephone);
		});
	</script>
</body>
</html>
