#! /bin/sh

curl  http://localhost:11500/users?sort=email,desc\&sort=id,desc | jq
