db.createUser({
    user: "planning-import-user",
    pwd: "planning-import-pass",
    roles: [
        {role: "readWrite", db: "planning-import"}
    ]
});