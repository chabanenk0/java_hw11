<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
    <title>GuestBook</title>
</head>
<body>
    <h2>Review form</h2>
    <div class="guestbook-form">
        <form name="feedback" method="post">
            <div class="form-row">
                <label for="feedback-name">Name:</label>
                <input type="text" name="name" id="feedback-name" minlength="1" maxlength="255">
            </div>
            <div class="form-row">
                <label for="feedback-message">Message:</label>
                <textarea rows="5" name="message" id="feedback-message" minlength="1"></textarea>
            </div>
            <div class="form-row">
                <label for="feedback-rating">Rating:</label>
                <select name="rating" id="feedback-rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            <input type="submit" value="Submit">
        </form>
    </div>
    <h2>Recent reviews:</h2>
    <div class="reviews-list">
        <c:forEach items="${reviews}" var="review">
            <div class="review">
                <span class="name"><c:out value="${review.name}" /></span>
                <span class="message"><c:out value="${review.name}" /></span>
                <span class="rating">My rating: <c:out value="${review.rating}" /></span>
                <span class="rating">Date: <c:out value="${review.date}" /></span>
            </div>
        </c:forEach>

    </div>
</body>
</html>
