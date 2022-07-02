<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Hello File Upload</h1>

    <section>API for upload:
        <select name="postPath" id="postPath">
            <option value="standard">standard</option>
            <option value="commons">commons</option>
            <option value="commons-streaming">commons-streaming</option>
        </select>
    </section>

    <section>
        <form method="POST" enctype="multipart/form-data" action="<% out.print(request.getContextPath()); %>/standard">
            <p>File to upload 1: <input type="file" name="uploadFile1"/></p>
            <p>File to upload 2: <input type="file" name="uploadFile2"/></p>
            <p>File to upload 3: <input type="file" name="uploadFile3"/></p>
            <p>Some data: <input type="text" name="textData" value="Hello!" maxlength="100"/></p>
            <p><input type="submit" value="upload"/></p>
        </form>
    </section>

    <script>
        const $postPath = document.querySelector('#postPath');
        const changeActionPath = () => {
            const contextPath = '<% out.print(request.getContextPath()); %>';
            const path = $postPath.options[$postPath.selectedIndex];
            document.forms[0].action = `\${contextPath}/\${path.value}`;
        };
        $postPath.addEventListener('change', changeActionPath);
        window.addEventListener('load', changeActionPath);
    </script>
</body>
</html>
