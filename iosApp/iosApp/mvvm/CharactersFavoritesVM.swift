//
//  CharactersFavoritesVM.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 12/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class CharactersFavoritesVM : CharactersFavoritesViewModel, ObservableObject {
    @Published var listCharactersFavorites: [Character] = []
        
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { data in
            let uiState = data as! CharactersFavoritesContractState
            let stateRequest = uiState.stateRequest
            
            switch stateRequest {
            case _ as StateRequest.Success:
                self.listCharactersFavorites = uiState.charactersFavorites
            default:
                break
            }
        })
    }
}
