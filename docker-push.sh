#!/bin/bash

read -p "Docker username: " docker_user
read -s -p "Docker password: " docker_psw

echo "$docker_psw" | docker login --username "$docker_user" --password-stdin

group=(`cat gradle.properties | grep "group" | cut -d= -f2`)
name=(`cat gradle.properties | grep "serviceName" | cut -d= -f2`)
image_tag=(`cat gradle.properties | grep "serviceVersion" | cut -d= -f2`)

echo "Pushing JWT service docker image tagged as $image_tag to $group/$name..."
docker push $group/$name:"$image_tag" \
	&& echo "Pushed $group/$name:$image_tag successfully.";
