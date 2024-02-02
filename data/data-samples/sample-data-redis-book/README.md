# sample-data-redis-book

## Description

- This is a sample data module demonstrating synapse-data-redis.

## Usage

- Use the startup.sh script to start the redis docker instance that also creates the BOOK table
      - If you used a different port then replace the `6379:6379` with the port from the command in `startup.sh`.
- Run the tests inside `BookRepositoryIT`
- Use the shutdown.sh script to stop the redis instance
