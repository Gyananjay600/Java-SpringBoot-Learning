<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { max-width: 320px; }
        label { display: block; margin-top: 12px; font-weight: bold; }
        input { width: 100%; padding: 6px; margin-top: 4px; box-sizing: border-box; }
        button { margin-top: 18px; padding: 8px 16px; background: #2b6cb0; color: white; border: none; border-radius: 4px; }
    </style>
</head>
<body>
<h1>Add Student</h1>
<form action="${pageContext.request.contextPath}/view/students" method="post">
    <label>Name</label>
    <input type="text" name="name" required />

    <label>Email</label>
    <input type="email" name="email" required />

    <button type="submit">Save</button>
</form>
</body>
</html>