//
//  CharacterDetailVM.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 12/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class CharacterDetailVM : CharacterDetailViewModel, ObservableObject {
    @Published var character: Character = Character(id: 1, name: "", status: Status.alive, species: "", gender: Gender.female, origin: "", location: "", image: "")
    @Published var isFavorite: Bool = false
    @Published var showAlert: Bool = false
    
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { data in
            let state = data as! CharacterDetailContractState
            
            self.isFavorite = state.isFavorite
            
            switch state.character {
                case let success as BasicUiStateSuccess<Character>:
                    self.character = success.data!
                default:
                    break
                }
            }
        )
        
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

