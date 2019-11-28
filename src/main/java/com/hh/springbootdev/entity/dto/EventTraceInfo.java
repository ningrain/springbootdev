/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: EventTraceInfo.java</p>
 *
 * @author jiangningning
 * @date 2019/11/27
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2019/11/27 Create
 */
package com.hh.springbootdev.entity.dto;

import com.hh.ddos.entity.Msg;
import lombok.Data;

import java.util.*;

/**
 * <p>Title: EventTraceInfo</p>
 * <p>Description: </p>
 * @author jiangningning
 */
@Data
public class EventTraceInfo {

    // 事件id
    private String eventId;
    // 各inIP溯源信息
    private List<DstIpTraceInfo> dstIpTraceInfos;

    public static void main(String[] args) {
        Msg.EventReport event = Msg.EventReport.newBuilder().build();
        List<Msg.InsideIpMsg> inIpList = event.getInIpList();
        Map<PointType, List<PointData>> pointDataMap = new HashMap<>();
        inIpList.forEach(insideIpMsg -> {
            List<Msg.PathMsg> pathList = insideIpMsg.getPathList();
            pathList.forEach(pathMsg -> {
                List<Msg.RouterMsg> routerList = pathMsg.getRouterList();
                routerList.forEach(routerMsg -> {
                    // RouterDto routerInfo = getRouterInfo();
                    RouterDto routerInfo = new RouterDto();
                    Node node = new Node();
                    node.setLevel(routerInfo.getLevel());
                    node.setLevelId(routerInfo.getLevelId());
                    node.setIspId(routerInfo.getIspId());
                    node.setId();
                    String routerId = routerMsg.getRouterId();
                    List<Msg.InIfIndexMsg> ifIndexMsgList = routerMsg.getInIfIndexList();
                    List<PointData> routerPoints = new ArrayList<>();
                    List<PointData> routerIfPoints = new ArrayList<>();
                    ifIndexMsgList.forEach(ifIndexMsg -> {
                        int inPort = ifIndexMsg.getInIfIndex();
                        String inIfIp = ifIndexMsg.getInIfIp();
                        List<Msg.SrcIpMsg> srcIPList = ifIndexMsg.getSrcIPList();
                        RouterInterface routerIf = new RouterInterface();
                        routerIf.setIp(routerId);
                        routerIf.setInPort(inPort);
                        routerIf.setInIP(inIfIp);
                        routerIf.setId();
                        routerIf.setAvgBps(ifIndexMsg.getBps());
                        routerIf.setMaxBps(ifIndexMsg.getBps());
                        List<PointData> srcIpPoints = new ArrayList<>();
                        srcIPList.forEach(srcIpMsg -> {
                            SrcIp srcIp = new SrcIp();
                            srcIp.setSrcIP(srcIpMsg.getSrcIP());
                            srcIp.setAvgBps(srcIpMsg.getBps());
                            srcIp.setMaxBps(srcIpMsg.getBps());
                            PointData<SrcIp> srcIpPoint = new PointData(srcIpMsg.getSrcIP(), routerIf.getId(), srcIp);
                            srcIpPoints.add(srcIpPoint);
                        });
                        pointDataMap.put(PointType.srcIP, srcIpPoints);
                        PointData<RouterInterface> routerIfPointData = new PointData(routerIf.getId(), routerId, routerIf);
                        routerIfPoints.add(routerIfPointData);

                        RouterDto routerDto = new RouterDto();
                        routerDto.setIp(routerId);
                        routerDto.setAvgBps(ifIndexMsg.getBps());
                        routerDto.setMaxBps(ifIndexMsg.getBps());
                        PointData<RouterDto> routerPointData = new PointData(routerId, node.getId(), routerDto);
                        routerPoints.add(routerPointData);
                    });
                    pointDataMap.put(PointType.routerIf, routerIfPoints);
                    pointDataMap.put(PointType.router, routerPoints);
                });
            });
        });
    }

    private EventTraceInfo getEventTraceInfo(Map<PointType, List<PointData>> pointDataMap) {
        return null;
    }

    private List<Node> getNodes(List<PointData<RouterDto>> routerPoints) {
        List<Node> nodes = new ArrayList<>();
        routerPoints.forEach(routerDtoPointData -> {
            RouterDto routerDto = routerDtoPointData.getData();
            Node node = new Node();
            node.setLevel(routerDto.getLevel());
            node.setLevelId(routerDto.getLevelId());
            node.setIspId(routerDto.getIspId());
            node.setType(NodeType.router);
            // node.setAvgBps(routerDto.);
            nodes.add(node);
        });
        return nodes;
    }


}
