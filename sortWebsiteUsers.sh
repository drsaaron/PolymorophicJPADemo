#! /bin/sh

curl http://localhost:11500/users?sort=email,desc | jq
