#! /bin/sh

curl http://localhost:11500/transactions-hateos/search/findByDetailType?detailType=1 | jq
