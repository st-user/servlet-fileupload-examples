<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Hello File Upload</h1>

    <section>
        <form method="POST" enctype="multipart/form-data" action="<% request.getContextPath(); %>/standard">
            <p>File to upload 1: <input type="file" name="uploadFile1"/></p>
            <p>File to upload 2: <input type="file" name="uploadFile2"/></p>
            <p>File to upload 3: <input type="file" name="uploadFile3"/></p>
            <p>Some data: <input type="text" name="textData" value="Hello!" maxlength="100"/></p>
            <p><input type="submit" value="upload"/></p>
        </form>
    </section>

    <section>
        <select name="postPath" id="postPath">
            <option value="standard">standard</option>
            <option value="commons">commons</option>
            <option value="commons-streaming">commons-streaming</option>
        </select>
    </section>


    <script>
        const $postPath = document.querySelector('#postPath');
        const changeActionPath = () => {
            const contextPath = '<% request.getContextPath(); %>';
            const path = $postPath.options[$postPath.selectedIndex];
            document.forms[0].action = `\${contextPath}/\${path.value}`;
        };
        $postPath.addEventListener('change', changeActionPath);
        window.addEventListener('load', changeActionPath);
    </script>
</body>
</html>
