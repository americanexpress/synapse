drop SCHEMA IF EXISTS ${schemaName} CASCADE;
create SCHEMA ${schemaName};

SET
SCHEMA_SEARCH_PATH TO ${schemaName};
