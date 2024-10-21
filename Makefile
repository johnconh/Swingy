all:
	docker compose up -d

run:
	@cd RPGgame/; \
	read -p "Enter mode console o gui: " mode; \
	./run.sh $${mode}
down:
	docker compose down

postgres:
	docker exec -it postgres psql -U jdasilva -d swingy -h localhost -W

clean: down
	yes | docker system prune -a

.PHONY: all run down postgres clean