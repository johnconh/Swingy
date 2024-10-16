all:
	docker compose up -d

down:
	docker compose down

postgres:
	docker exec -it postgres psql -U jdasilva -d swingy -h localhost -W

clean: down
	yes | docker system prune -a