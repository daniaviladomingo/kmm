//
//  CharacterDetailVM.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 12/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class CharacterDetailVM : CharacterDetailViewModel, ManagementResourceState {
    @Published var character: Character = Character(id: 1, name: "", status: Status.alive, species: "", gender: Gender.female, origin: "", location: "", image: "")
    @Published var isFavorite: Bool = false
    @Published var showAlert: Bool = false
    
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { data in            
            let state = data as! CharacterDetailContractState
            
            self.isFavorite = state.isFavorite
            
            self.managementResourceState(
                resourceState: state.character,
                successClosure: { data in
                    self.character = data!
                }
            )
        })
        
        collect(flow: effect) { uiEffect in
            let effect = (uiEffect as! CharacterDetailContractEffect)
            
            switch effect {
                case CharacterDetailContractEffect.CharacterAdded.shared:
                    self.isFavorite = true
                case CharacterDetailContractEffect.CharacterRemoved.shared:
                    self.isFavorite = false
                default:
                    break
            }
            
            self.showAlert = true
        }
    }
}

