use mydb;
# SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
# SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
# SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
--  event log example
-- POST /events
-- {
--  "type": "REVIEW",
--  "action": "ADD", /* "MOD", "DELETE" */
--  "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
--  "content": "좋아요!",
--  "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
--  "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
--  "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
-- }

START TRANSACTION;

INSERT INTO `mydb`.`user` (`user_id`, `created_at`)
VALUES ('uede0ef2-92b7-4817-a5f3-0c575361f745', NOW());

INSERT INTO `mydb`.`user` (`user_id`, `created_at`)
VALUES ('uede0ef2-92b7-4817-a5f3-0c575361f746', NOW());

INSERT INTO `mydb`.`user`(`user_id`, `created_at`)
VALUES ('uede0ef2-92b7-4817-a5f3-0c575361f747', NOW());

INSERT INTO `mydb`.`place` (`place_id`, `created_at`)
VALUES ('pe4baf1c-5acb-4efb-a1af-eddada31b00f', NOW());

INSERT INTO `mydb`.`place`(`place_id`, `created_at`)
VALUES ('pe4baf1c-5acb-4efb-a1af-eddada31b00g', NOW());

INSERT INTO `mydb`.`place`(`place_id`, `created_at`)
VALUES ('pe4baf1c-5acb-4efb-a1af-eddada31b00h', NOW());

# check first review
SET @PLACE_REVIEW_CNT = (SELECT place_place_id
                         FROM `mydb`.`review`
                         where place_place_id = 'pe4baf1c-5acb-4efb-a1af-eddada31b00f');
# SELECT @PLACE_REVIEW_CNT;

-- Write first review
INSERT INTO `mydb`.`review`(review_id, user_user_id, place_place_id, content, attach_photo_ids, status_type,
                            created_at,
                            modified_at)
VALUES ('r40a0658-dc5f-4878-9381-ebb7b2667772', 'uede0ef2-92b7-4817-a5f3-0c575361f745',
        'pe4baf1c-5acb-4efb-a1af-eddada31b00f', 'first_review', '[ "attachPhotoId1", "attachPhotoId2" ]',
        'NORMAL',
        NOW(), NOW())
ON DUPLICATE KEY UPDATE content='first_review review',
    attach_photo_ids='[ "attachPhotoId1", "attachPhotoId2" ]',
    status_type='NORMAL',
    modified_at=NOW();

# select * from review;
insert into review_point_detail (review_review_id, content_point, attach_photo_point, first_review_point,
                                 total_point)
VALUES ('r40a0658-dc5f-4878-9381-ebb7b2667772', 1, 1, COALESCE(@PLACE_REVIEW_CNT, 1), 3);
# select * from review_point_detail;
insert into review_point_detail_history (review_point_detail_review_review_id, user_user_id, content_point,
                                         attach_photo_point, first_review_point, total_point, created_at)
VALUES ('r40a0658-dc5f-4878-9381-ebb7b2667772', 'uede0ef2-92b7-4817-a5f3-0c575361f745', 1, 1,
        COALESCE(@PLACE_REVIEW_CNT, 1), 3, NOW());
# select * from review_point_detail_history order by review_point_detail_history_id desc;

-- DELETE
UPDATE `mydb`.`review`
SET status_type='DELETE',
    modified_at=NOW()
WHERE review_id = 'r40a0658-dc5f-4878-9381-ebb7b2667772';
update review_point_detail
set content_point=0,
    attach_photo_point=0,
    first_review_point=0,
    total_point=0
where review_review_id = 'r40a0658-dc5f-4878-9381-ebb7b2667772';
insert into review_point_detail_history (review_point_detail_review_review_id, user_user_id, content_point,
                                         attach_photo_point, first_review_point, total_point, created_at)
VALUES ('r40a0658-dc5f-4878-9381-ebb7b2667772', 'uede0ef2-92b7-4817-a5f3-0c575361f745', 0, 0, 0, 0, NOW());

-- ReWrite review to same place
INSERT INTO `mydb`.`review`(review_id, user_user_id, place_place_id, content, attach_photo_ids, status_type,
                            created_at,
                            modified_at)
VALUES ('r40a0658-dc5f-4878-9381-ebb7b2667772', 'uede0ef2-92b7-4817-a5f3-0c575361f745',
        'pe4baf1c-5acb-4efb-a1af-eddada31b00f', 'content', '[ "photoId1" ]', 'NORMAL', NOW(), NOW())
ON DUPLICATE KEY UPDATE content='rewrite review',
    attach_photo_ids='[ "photoId1" ]',
    status_type='NORMAL',
    modified_at=NOW();
# another user took the first review point
update review_point_detail
set content_point=1,
    attach_photo_point=1,
    first_review_point=0,
    total_point=2
where review_review_id = 'r40a0658-dc5f-4878-9381-ebb7b2667772';
insert into review_point_detail_history (review_point_detail_review_review_id, user_user_id, content_point,
                                         attach_photo_point, first_review_point, total_point, created_at)
VALUES ('r40a0658-dc5f-4878-9381-ebb7b2667772', 'uede0ef2-92b7-4817-a5f3-0c575361f745', 1, 1, 0, 2, NOW());

-- check total point of the user except delete review
select sum(total_point)
from user
         inner join review on user.user_id = review.user_user_id and status_type = 'NORMAL'
         inner join review_point_detail rpd on review.review_id = rpd.review_review_id
where user_id = 'uede0ef2-92b7-4817-a5f3-0c575361f745';

-- check user review point history
select rpdh.*
from user
         inner join review_point_detail_history rpdh on user.user_id = rpdh.user_user_id
where user_id = 'uede0ef2-92b7-4817-a5f3-0c575361f745'
order by review_point_detail_history_id desc;
ROLLBACK;




