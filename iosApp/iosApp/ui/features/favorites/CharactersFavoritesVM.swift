//
//  CharactersFavoritesVM.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 12/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class CharactersFavoritesVM : CharactersFavoritesViewModel, ManagementResourceState {
    @Published var listCharactersFavorites: [Character] = []
        
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { data in
            let state = data as! CharactersFavoritesContractState
            
            self.managementResourceState(
                resourceState: state.charactersFavorites,
                successClosure: { data in
                    self.listCharactersFavorites = data as! [Character]
                }
            )
        })
    }
}
