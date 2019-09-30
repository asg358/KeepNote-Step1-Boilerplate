
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KeepNote</title>
</head>
<body>
	<!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
		 button. Handle errors like empty fields -->
    <form action="/saveNote">
      Note ID :<br>
      <input type="text" name="noteId" ><br>
      Note Title :<br>
      <input type="text" name="noteTitle" ><br>
      Note Status :<br>
      <input type="text" name="noteStatus" ><br>
      Note Content :<br>
      <input type="text" name="noteContent" ><br><br>
      <input type="submit" value="Submit">
    </form>

	<!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->

    <h1 align="center">List of Notes :</h1>
    <table width="50%" align="center">
          <tr>
                <th width="20%" align="center">Id</th>
                <th width="20%" align="center">Title</th>
                <th width="20%" align="center">Status</th>
                <th width="20%" align="center">Content</th>
                <th width="20%" align="center">Delete</th>
          </tr>
          
          <c:forEach items="${notes}" var="note">
                <tr>
                      <td width="20%" align="center">${note.noteID}</td>
                      <td width="20%" align="center">${note.noteTitle}</td>
                      <td width="20%" align="center">${note.noteStatus}</td>
                      <td width="20%" align="center">${note.noteContent}</td>
                      <td width="20%" align="center"><a href="/deleteNote?noteId=${note.noteID}">Delete</a></td>
                </tr>
          </c:forEach>
    </table>

</body>
</html>