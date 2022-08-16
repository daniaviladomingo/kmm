//
//  CharacterDetailVM.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 12/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared
import Combine

class CharacterDetailVM : CharacterDetailViewModel, ObservableObject {
    @Published var state: CharacterDetailContractState =
    CharacterDetailContractState(character: BasicUiStateIdle.shared, isFavorite: false)
    @Published var isFavorite: Bool = false
    @Published var showAlert: Bool = false
    
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { state in
            self.state = state as! CharacterDetailContractState
        })
        
        collect(flow: effect) { uiEffect in
            let effect = (uiEffect as! CharacterDetailContractEffect)
            
            switch effect {
            case is CharacterDetailContractEffectCharacterAdded:
                    self.isFavorite = true
            case is CharacterDetailContractEffectCharacterRemoved:
                    self.isFavorite = false
                default:
                    break
            }
            
            self.showAlert = true
        }
    }
}

