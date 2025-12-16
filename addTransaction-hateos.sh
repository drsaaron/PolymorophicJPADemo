#! /bin/sh

# add a transaction.  HATEOS endpoint isn't working due to circular references.  For now use the explicitly
# built API.
curl -X POST -H "Content-Type:application/json" -d "@newTransactionDetailType1.json" http://localhost:11500/transactionDetailsType1-hateos
