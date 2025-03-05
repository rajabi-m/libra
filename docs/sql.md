### SQL Queries for the database

1. Creating the borrow_counts_view for BorrowCountViewRepository

```sql
create view borrow_counts_view as
select a.id, count(bh.id) as count from borrow_history as bh
right join asset as a on a.id = bh.asset_id
group by a.id;
```
