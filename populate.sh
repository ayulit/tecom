#!/bin/sh

jq -c '.[]' data.json | while read -r JSON; do
  curl -s -X POST localhost:8080/api/cars -H "Content-type:application/json" -d "$JSON" | jq -r '.id'
  # shellcheck disable=SC2154
  printf "%s" "${id}"
done

printf "Database populated.\n"
