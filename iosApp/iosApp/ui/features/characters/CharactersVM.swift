//
//  CharactersViewModel.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 6/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class CharactersVM: CharactersViewModel, ManagementResourceState {
    @Published var listCharacters: [Character] = []
    
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { data in
            let state = data as! CharactersContractState
            
            self.managementResourceState(
                resourceState: state.characters,
                successClosure: { data in
                    self.listCharacters = data as! [Character]
                }
            )
        })
    }
}
