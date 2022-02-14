package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.GroupDto;
import com.bamcoreport.web.api.identity.dto.services.IMapClassWithDto;
import com.bamcoreport.web.api.identity.entities.Group;
import com.bamcoreport.web.api.identity.helpers.HelpUpdate;
import com.bamcoreport.web.api.identity.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService implements  IGroupService{

    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(GroupService.class.getName());


    @Autowired
    GroupRepository groupRepository;

    @Autowired
    IMapClassWithDto<Group, GroupDto> groupMapping;


    // ---- Gel all Groups ----------------------------------------------------------------------

    @Override
    public List<GroupDto> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groupMapping.convertListToListDto(groups, GroupDto.class);
    }
    //------------------------------------------------------------------------------------------------



    //----- Add groupe -----------------------------------------------------------------------------

    @Override
    public GroupDto addGroup(GroupDto group) {
        Group groupEntity=groupMapping.convertToEntity(group,Group.class);
        groupEntity=groupRepository.save(groupEntity);
        return groupMapping.convertToDto(groupEntity, GroupDto.class);
    }

    //-----------------------------------------------------------------------------------------------


    //---- delete groupe ---------------------------------------------------------------------------

    @Override
    public boolean deleteGroup(long id) {

            try {
               groupRepository.deleteById(id);
            }catch (Exception ex){
                log4j.error(ex.getMessage());
                return false;
            }
            return true;
        }


    //-----------------------------------------------------------------------------------------------




    //---- update groupe ---------------------------------------------------------------------------

    @Override
    public GroupDto updateGroup(GroupDto groupe) {
        GroupDto saved = groupMapping.convertToDto(groupRepository.getById(groupe.getId()),GroupDto.class); // DB
        HelpUpdate.copyNonNullProperties(groupe,saved);
        Group groupEntity=groupMapping.convertToEntity(saved,Group.class);
        groupEntity=groupRepository.save(groupEntity);
        return groupMapping.convertToDto(groupEntity, GroupDto.class);
    }

    //----------------------------------------------------------------------------------------------

}
