#! /bin/sh

curl -i -X POST -H "Content-Type: application/json" -d "@newWebsiteUser.json" http://localhost:11500/users
