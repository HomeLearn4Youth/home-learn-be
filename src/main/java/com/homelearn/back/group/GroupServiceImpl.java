package com.homelearn.back.group;

import com.homelearn.back.group.dto.*;
import com.homelearn.back.group.entity.Group;
import com.homelearn.back.group.exception.GroupException;
import com.homelearn.back.house.ApartService;
import com.homelearn.back.house.dto.ApartListInputSpec;
import com.homelearn.back.house.dto.ApartOutputSpec;
import com.homelearn.back.like.LikeMapper;
import com.homelearn.back.like.exception.LikeException;
import com.homelearn.back.news.CrawlerToObjectMaker;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.homelearn.back.group.exception.GroupErrorCode.*;
import static com.homelearn.back.like.exception.LikeErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupMapper groupMapper;
    private final LikeMapper likeMapper;
    private final ApartService apartService;
    private final CrawlerToObjectMaker maker;
    @Override
    public void addGroup(GroupParam param) {
        groupMapper.addGroup(param);
    }


    @Override
    public void deleteGroup(Long groupId, Long loginUserId) {
        if (groupMapper.findGroup(groupId)
                .orElseThrow(()->new GroupException(NOT_EXISTS_GROUP))
                .getUserId() != loginUserId) throw new GroupException(FORBIDDEN_GROUP_ITEM);
        groupMapper.deleteGroup(groupId);
    }

    @Override
    public List<Group> findGroupListByUserId(Long userId) {
        return groupMapper.findGroupListByUserId(userId);
    }

    @Override
    public void addGroupItem(GroupItemInputSpec groupItemInputSpec, Long loginUserId) {
        if(likeMapper.findLikeByLikeId(groupItemInputSpec.getLikeId())
                .orElseThrow(()-> new LikeException(NOT_EXISTS_LIKE_APART))
                .getUserId()!=loginUserId) throw new LikeException(FORBIDDEN_LIKE_APART);
        groupMapper.findGroup(groupItemInputSpec.getGroupId())
                .orElseThrow(()->new GroupException(NOT_EXISTS_GROUP));
        if(groupMapper.findGroupCount(GroupListCountParam
                .builder()
                .groupId(groupItemInputSpec.getGroupId())
                .userId(loginUserId)
                .build())>5) throw new GroupException(TO_MANY_GROUP_ITEM);
        groupMapper.findGroupItem(groupItemInputSpec)
                .ifPresent(groupItem -> {throw new GroupException(ALREADY_IN_GROUP_ITEM);});
        groupMapper.addGroupItem(groupItemInputSpec);
    }

    @Override
    public void deleteGroupItem(GroupItemInputSpec groupItemInputSpec, Long loginUserId) {
        if(likeMapper.findLikeByLikeId(groupItemInputSpec.getLikeId())
                .orElseThrow(()-> new LikeException(NOT_EXISTS_LIKE_APART))
                .getUserId()!=loginUserId) throw new LikeException(FORBIDDEN_LIKE_APART);
        if(groupMapper.findGroup(groupItemInputSpec.getGroupId())
                .orElseThrow(()->new GroupException(NOT_EXISTS_GROUP))
                .getUserId()!=loginUserId) throw new GroupException(FORBIDDEN_GROUP_ITEM);
        groupMapper.findGroupItem(groupItemInputSpec)
                .orElseThrow(()->new GroupException(NOT_EXISTS_GROUP_ITEM));
        groupMapper.deleteGroupItem(groupItemInputSpec);
    }

    @Override
    public GroupItemListOutputSpec findGroupItemList(ApartListInputSpec inputSpec, User user) {
        List<ApartOutputSpec> groupApartList = apartService.getApartList(inputSpec, user).stream()
                .map(m -> {
                    if (m.getAptImg() == null) {
                        return new ApartOutputSpec().houseJoinLikeToApartOutputSpec(m, maker.getImg(m));
                    } else {
                        return new ApartOutputSpec().houseJoinLikeToApartOutputSpec(m);
                    }
                })
                .collect(Collectors.toList());
        //마지막 인덱스
        if (groupApartList.size()==0){
            return GroupItemListOutputSpec.builder()
                    .startX(null)
                    .startY(null)
                    .endX(null)
                    .endY(null)
                    .passList(null)
                    .items(groupApartList)
                    .build();
        } else if (groupApartList.size()==1) {
            return GroupItemListOutputSpec.builder()
                    .startX(groupApartList.get(0).getLng())
                    .startY(groupApartList.get(0).getLat())
                    .endX(groupApartList.get(0).getLng())
                    .endY(groupApartList.get(0).getLat())
                    .passList(null)
                    .items(groupApartList)
                    .build();
        } else {
            List<ApartOutputSpec> shortestPath=ShortestPath.findOptimalPath(groupApartList);
            ApartOutputSpec startApart = shortestPath.get(0);
            int endIdx = shortestPath.size() - 1;
            ApartOutputSpec endApart = shortestPath.get(endIdx);
            StringBuilder passList = new StringBuilder();
            for (int i = 1; i < endIdx; i++) {
                ApartOutputSpec passApart = shortestPath.get(i);
                if (i != endIdx) {
                    passList.append(passApart.getLng())
                            .append(",")
                            .append(passApart.getLat())
                            .append("_");
                } else {
                    passList.append(passApart.getLng())
                            .append(",")
                            .append(passApart.getLat());
                }
            }
            return GroupItemListOutputSpec.builder()
                    .startX(startApart.getLng())
                    .startY(startApart.getLat())
                    .endX(endApart.getLng())
                    .endY(endApart.getLat())
                    .passList(passList.toString())
                    .items(groupApartList)
                    .build();
        }
    }

}
