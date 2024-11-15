<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Panier</title>
    <link rel="stylesheet" href="styles.css"> <!-- Incluez vos styles CSS ici -->
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css"> <!-- Pour les icônes -->
</head>
<body>
<jsp:include page="css.jsp" />
<jsp:include page="maintophomme.jsp" />
<jsp:include page="headerhomme.jsp" />

<!-- Start Cart -->
<div class="cart-box-main">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="table-main table-responsive">
                    <c:if test="${not empty produits}">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Images</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Update</th>
                                    <th>Total</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Boucle à travers les produits -->
                                <c:forEach var="produit" items="${produits}">
                                    <tr>
                                        <td class="thumbnail-img">
                                            <a href="#">
                                                <img class="img-fluid" src="${produit.image}" alt="${produit.nom}" />
                                            </a>
                                        </td>
                                        <td class="name-pr">
                                            <a href="#">${produit.nom}</a>
                                        </td>
                                        <td class="price-pr">
                                            <p>${produit.prix} MAD</p>
                                        </td>
                                        <td class="quantity-box">
                                            <!-- Recherche de la quantité dans la liste paniers en fonction de l'ID du produit -->
                                            <c:set var="quantite" value="0"/>
                                            <c:forEach var="panier" items="${paniers}">
                                                <c:if test="${produit.idProduit == panier.produitId}">
                                                	<c:set var="produitId" value="${panier.produitId}"/>
                                                    <c:set var="quantite" value="${panier.quantite}"/>
                                                </c:if>
                                            </c:forEach>
                                            <!-- Affichage de la quantité -->
                                            <form method="post" action="PanierServlet">
    <input type="hidden" name="_method" value="PUT" />
    <input type="hidden" name="user_id" value="${param.user_id}">
    <input type="hidden" name="produit_id" value="${produitId}">
    <input type="number" name="quantite" value="${quantite}" step="1" min="1" class="c-input-text qty text" required>
    <button type="submit" class="update-box">Update</button>
</form>

                                            
                                        </td>
                                        <td>
                                        
                                        </td>
                                        <td class="total-pr">
                                            <!-- Calcul du total pour le produit -->
                                            <c:set var="total" value="0"/>
                                            <c:forEach var="panier" items="${paniers}">
                                                <c:if test="${produit.idProduit == panier.produitId}">
                                                    <c:set var="total" value="${produit.prix * panier.quantite}"/>
                                                </c:if>
                                            </c:forEach>
                                            <!-- Affichage du total -->
                                            <p>${total} MAD</p>
                                        </td>
                                        <td class="remove-pr">
    <form method="post" action="PanierServlet" onsubmit="return confirm('Voulez-vous vraiment supprimer cet article ?');">
        <!-- Champ caché pour spécifier la méthode DELETE -->
        <input type="hidden" name="_method" value="delete">
        <!-- Champs cachés pour transmettre les paramètres nécessaires -->
        <input type="hidden" name="produit_id" value="${produit.idProduit}">
        <input type="hidden" name="user_id" value="${param.user_id}"> <!-- Remplacez par la variable appropriée -->

        <!-- Bouton de soumission avec une icône -->
        <button type="submit" class="delete-button" style="background: none; border: none; padding: 0;">
            <i class="fas fa-times" style="color: red; cursor: pointer;"></i>
        </button>
    </form>
</td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty produits}">
                        <p>Your cart is empty.</p>
                    </c:if>
                </div>
            </div>
        </div>

        <!-- Reste du contenu du panier -->

    </div>
</div>
<!-- End Cart -->
<jsp:include page="js.jsp" />
<jsp:include page="footer.jsp" />
</body>
</html>
