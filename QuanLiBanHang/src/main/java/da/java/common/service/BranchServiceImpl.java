package da.java.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import da.java.common.entities.Branch;
import da.java.common.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;
    
    @Override
    public Branch getBranch(Long id) {
        return branchRepository.findOne(id);
    }

}
