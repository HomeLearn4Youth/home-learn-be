package com.homelearn.back.group;

import com.homelearn.back.group.dto.*;
import com.homelearn.back.group.entity.Group;
import com.homelearn.back.group.entity.GroupItem;
import com.homelearn.back.group.exception.GroupErrorCode;
import com.homelearn.back.group.exception.GroupException;
import com.homelearn.back.house.ApartMapper;
import com.homelearn.back.house.ApartService;
import com.homelearn.back.house.dto.ApartListInputSpec;
import com.homelearn.back.house.dto.ApartOutputSpec;
import com.homelearn.back.house.entity.HouseJoinLike;
import com.homelearn.back.like.LikeMapper;
import com.homelearn.back.like.dto.LikeParam;
import com.homelearn.back.like.exception.LikeErrorCode;
import com.homelearn.back.like.exception.LikeException;
import com.homelearn.back.news.CrawlerToObjectMaker;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.homelearn.back.group.exception.GroupErrorCode.*;
import static com.homelearn.back.like.exception.LikeErrorCode.*;

@Service
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
                    if (m.getAptImg()==null){
                        return new ApartOutputSpec().houseJoinLikeToApartOutputSpec(m,maker.getImg(m));
                    }else {
                        return new ApartOutputSpec().houseJoinLikeToApartOutputSpec(m);
                    }
                })
                .collect(Collectors.toList());

        if (groupApartList.size()==0){
            return GroupItemListOutputSpec.builder()
                    .startX(null)
                    .startY(null)
                    .endX(null)
                    .endY(null)
                    .passList(null)
                    .items(groupApartList)
                    .build();
        }
        int endIdx = groupApartList.size()-1;
        ApartOutputSpec startApart = groupApartList.get(0);
        ApartOutputSpec endApart = groupApartList.get(endIdx);
        StringBuilder passList = new StringBuilder();
        for (int i = 1; i <= endIdx-1; i++) {
            ApartOutputSpec passApart = groupApartList.get(i);
            if (i!=endIdx-1){
                passList.append(passApart.getLng())
                        .append(",")
                        .append(passApart.getLat())
                        .append("_");
            }else {
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


//    private static HouseJoinLike findNearestPoint(HouseJoinLike current, List<HouseJoinLike> houses, boolean[] visited) {
//        HouseJoinLike nearest = null;
//        double minDistance = Double.MAX_VALUE;
//        for (int i = 0; i < houses.size(); i++) {
//            if (!visited[i]) {
//                double distance = current.distance(houses.get(i));
//                if (distance < minDistance) {
//                    minDistance = distance;
//                    nearest = houses.get(i);
//                    visited[i] = true;
//                }
//            }
//        }
//        return nearest;
//    }
//    public static List<HouseJoinLike> findShortestPath(List<HouseJoinLike> houses) {
//        List<HouseJoinLike> path = new ArrayList<>();
//        boolean[] visited = new boolean[houses.size()]; // Tracking visited points
//
//        HouseJoinLike current = houses.get(0);
//        visited[0] = true; // Mark the first point as visited
//        path.add(current);
//
//        while (path.size() < houses.size()) {
//            HouseJoinLike next = findNearestPoint(current, houses, visited);
//            if (next != null) {
//                path.add(next);
//                current = next;
//            }
//        }
//        return path;
//    }
}
